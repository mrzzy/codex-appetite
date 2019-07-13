# depth estimation model
# mostly derieved from https://github.com/ialhashim/DenseDepth/blob/master/test.py with minor edits

from keras.models import load_model
from layers import BilinearUpSampling2D
from utils import predict, load_images, display_images
from matplotlib import pyplot as plt

import numpy as np
import skimage
from skimage.transform import resize

# DenseDepth model interface class
class Model:
    # load the pretrain model at the given path
    def __init__(self, model_path):
        # Custom object needed for inference and training
        custom_objects = {'BilinearUpSampling2D': BilinearUpSampling2D, 'depth_loss_function': None}
        # Load model into GPU / CPU
        self.model = load_model(model_path, custom_objects=custom_objects, compile=False)

    def predict(self, image):
        # save orginal image shape to ensure output hash same shape as image
        orig_shape = image.shape
        # prepare input for being used in by the modell
        image = resize(image, (640, 480), preserve_range=True, anti_aliasing=True)
        x = np.stack([np.clip(np.asarray(image, dtype=float) / 255, 0, 1)], axis=0)
        # perform prediction with model
        depth_map = predict(self.model, x, minDepth=10, maxDepth=1000, batch_size=2)
        # revert the shape of the image original :-1 to remove channel dimension
        depth_map = resize(depth_map, orig_shape[:-1] + (1,), order=1,
                           preserve_range=1, anti_aliasing=True)
        return np.squeeze(depth_map)

if __name__ == "__main__":
    model = Model("models/dense_depth_nyu.h5")
    img = skimage.io.imread("pizza.jpg")
    model.predict(img)
