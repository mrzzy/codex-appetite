FROM tensorflow/tensorflow:latest-gpu-py3-jupyter

RUN apt-get update && apt-get install -y git libsm6 libxext6 libxrender-dev \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

RUN mkdir -p deps/mask_rcnn
COPY deps/mask_rcnn/requirements.txt deps/mask_rcnn/requirements.txt

RUN pip install -r deps/mask_rcnn/requirements.txt
RUN pip install flask

# install pycocotools
RUN git clone https://github.com/waleedka/coco /tmp/pycoco
RUN cd /tmp/pycoco/PythonAPI && make && make install && python setup.py install

COPY . /tf
ENV PYTHONPATH=deps/mask_rcnn:${PYTHONPATH}

ENTRYPOINT bash
