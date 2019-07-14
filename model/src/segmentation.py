# segmentation model - mask rcnn
# mostly derived from:https://github.com/matterport/Mask_RCNN/blob/master/samples/demo.ipynb with minor edits

import os
import sys
import random
import math
import numpy as np
import skimage.io
import matplotlib
import matplotlib.pyplot as plt

from mrcnn import utils
import mrcnn.model as modellib
from mrcnn import visualize
from samples.coco import coco
from skimage.transform import resize

# Mask RCNN 
class Model:
    class InferenceConfig(coco.CocoConfig):
        # Set batch size to 1 since we'll be running inference on
        # one image at a time. Batch size = GPU_COUNT * IMAGES_PER_GPU
        GPU_COUNT = 1
        IMAGES_PER_GPU = 1

    # COCO Class names
    # Index of the class in the list is its ID. For example, to get ID of
    # the teddy bear class, use: class_names.index('teddy bear')
    class_names = ['BG', 'person', 'bicycle', 'car', 'motorcycle', 'airplane',
                   'bus', 'train', 'truck', 'boat', 'traffic light',
                   'fire hydrant', 'stop sign', 'parking meter', 'bench', 'bird',
                   'cat', 'dog', 'horse', 'sheep', 'cow', 'elephant', 'bear',
                   'zebra', 'giraffe', 'backpack', 'umbrella', 'handbag', 'tie',
                   'suitcase', 'frisbee', 'skis', 'snowboard', 'sports ball',
                   'kite', 'baseball bat', 'baseball glove', 'skateboard',
                   'surfboard', 'tennis racket', 'bottle', 'wine glass', 'cup',
                   'fork', 'knife', 'spoon', 'bowl', 'banana', 'apple',
                   'sandwich', 'orange', 'broccoli', 'carrot', 'hot dog', 'pizza',
                   'donut', 'cake', 'chair', 'couch', 'potted plant', 'bed',
                   'dining table', 'toilet', 'tv', 'laptop', 'mouse', 'remote',
                   'keyboard', 'cell phone', 'microwave', 'oven', 'toaster',
                   'sink', 'refrigerator', 'book', 'clock', 'vase', 'scissors',
                   'teddy bear', 'hair drier', 'toothbrush']

    # load maskrcnnn model for inference
    def __init__(self, model_path):
        config = self.InferenceConfig()
        self.model = modellib.MaskRCNN(mode="inference", model_dir="logs" , config=config)
        self.model.load_weights(model_path, by_name=True)

    # perform segmentation on the given image returning the bouding boxesf
    #segmentation masks, scores, labels, and classes
    def predict(self, image):
        results = self.model.detect([ image], verbose=1)
        # unpack results
        r = results[0]
        boxes, masks, scores, class_ids = r["rois"], r["masks"], r["scores"], r["class_ids"]
        classes = [ self.class_names[i] for i in  class_ids ]

        return boxes, masks, scores, classes

if __name__ == "__main__":
    model = Model("models/mask_rcnn_coco.h5")
    img = skimage.io.imread("pizza.jpg")
    ax = plt.gca()
    results = model.model.detect([img], verbose=1)[0]
    visualize.display_instances(img, results["rois"], results["masks"],
                                results["class_ids"], Model.class_names,
                                results["scores"], ax=ax)
    plt.savefig("seg.jpg")
