# food portion model

import numpy as np
import segmentation
import depth

import skimage
from skimage.transform import resize

DEPTH_WEIGHT = 15

# utilites
class_whitelist = [
    'banana', 'apple', 'sandwich', 'orange', 'broccoli', 'carrot', 'hot dog',
    'pizza', 'donut', 'cake'
]
def top_box_mask(results):
    boxes, masks, scores, classes = results
    best_score = 0
    best_mask = None
    best_box = None
    for i, (score, label) in enumerate(zip(scores, classes)):
        if label in class_whitelist:
            if score > best_score:
                best_mask = masks[:,:,i]
                best_box = boxes[i]
                best_score = score
    return best_box, best_mask

# compute portion size given mask and map
def compute_portion(box, mask, depth_map):
    if mask is None: return 0 # nothing to see here
    # compute mask coverage (0 - 1)
    y1, x1, y2, x2 = box
    coverage = np.sum(mask) / (abs(y2 - y1) * abs(x2 - x1))

    # compute scaled (0 - 1)  mean depth
    depth_map *= mask  # zero out depth of irrelevant not part of instance
    mean_depth = (np.sum(depth_map) / len(np.ravel(np.nonzero(mask))))

    # compute actual portion size segment mask weighted by depth
    return coverage + mean_depth * DEPTH_WEIGHT

class PortionModel:
    def __init__(self):
        self.segment_model = segmentation.Model("models/mask_rcnn_coco.h5")
        self.depth_model = depth.Model("models/dense_depth_nyu.h5")

    # perform prediction of portion size   on the image with the given file
    def predict_file(self, img_file):
        img = skimage.io.imread(img_file)
        return self.predict(img)

    # perform prediction of portion size on the given image
    def predict(self, img):
        img = resize(img, (512, 512), preserve_range=True)
        # perform image segmentation to obtain top mask
        segment_results = self.segment_model.predict(img)
        box, mask = top_box_mask(segment_results)
        # perform depth estimation to obtain mean depth estimation   
        depth_map =  self.depth_model.predict(img)
        # compute portion size
        portion_size = compute_portion(box, mask, depth_map)

        return portion_size

if __name__ == "__main__":
    model = PortionModel()
    img = skimage.io.imread("pizza.jpg")
    full_portion = model.predict(img)

    img = skimage.io.imread("eaten_pizza.jpg")
    part_portion = model.predict(img)

    img = skimage.io.imread("slice_pizza.jpg")
    slice_portion = model.predict(img)

    print("full: ", full_portion, " part: ", part_portion,
         "slice: ", slice_portion)
