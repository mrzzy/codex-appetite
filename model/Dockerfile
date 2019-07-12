FROM tensorflow/tensorflow:latest-gpu-py3-jupyter

RUN mkdir -p deps/mask_rcnn
COPY deps/mask_rcnn/requirements.txt deps/mask_rcnn/requirements.txt

RUN pip install -r deps/mask_rcnn/requirements.txt
RUN pip uninstall -y tensorflow && pip install tensorflow-gpu

COPY . /tf
ENV PYTHONPATH=deps/mask_rcnn:${PYTHONPATH}

ENTRYPOINT bash
