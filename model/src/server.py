#
# Food Portion API
#

import os
import io
import skimage
import numpy as np
from flask import Flask, flash, request, redirect, url_for
from model import PortionModel

model = None
app = Flask(__name__)

def process_file(f):
    img = skimage.io.imread(f.stream)
    return model.predict(img)

# check status of food api
@app.route('/')
def status():
    return "Food Portion API is running"

@app.route("/api/portion/diff", methods=["POST"])
def api_portion_diff():
    before_portion = process_file(request.files["before"])
    after_portion = process_file(request.files["after"])

    diff = int((before_portion - after_portion) * 100)
    print("api diff: ", diff)
    return str(diff)

if __name__ == "__main__":
    model = PortionModel()
    print("warming up the model...")
    # warm up the model
    img = skimage.io.imread("pizza.jpg")
    model.predict(img)
    print("DONE")
    app.run("0.0.0.0", port=8080, threaded=False)
