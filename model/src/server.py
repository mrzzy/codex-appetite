#
# Food Portion API
#

import os
import io
import skimage
import numpy as np
from flask import Flask, flash, request, redirect, url_for
app = Flask(__name__)

def process_file(f):
    img = skimage.io.imread(f.stream)
    return model.predict(img)

def diff(before, after):
    b_mask = top_mask(before)
    a_mask = top_mask(after)

    return weighted_mask(b_mask) - weighted_mask(a_mask)

# check status of food api
@app.route('/')
def hello_world():
    return "Food Portion API is running"

@app.route("/api/portion/diff", methods=["POST"])
def api_portion_diff():
    before = process_file(request.files["before"])
    after = process_file(request.files["after"])

    return str(diff(before, after))

if __name__ == "__main__":
    model = segmentation.Model("models/mask_rcnn_coco.h5")
    app.run("0.0.0.0", port=8080, threaded=False)
