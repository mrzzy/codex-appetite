# Food Portion Model

## Introduction
The food portion model to detect the portion of food given and image of the food.
It does this by first performing food-image segementation on the food.
After that it computes portion by computing the area that has been segmented out 
together adjusted by the distance from the camera and resolution of the image.

## Citations
~AKA smart peoples work we hav used~
Food Portion Model uses MaskRCNN model to perform image segmentation
https://github.com/matterport/Mask_RCNN.git

Food Portion Model uses DenseDpeth model to perform depth estimation
https://github.com/ialhashim/DenseDepth.git
