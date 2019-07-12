# Food Portion Model

## Introduction
The food portion model to detect the portion of food given and image of the food.
It does this by first performing food-image segementation on the food.
After that it computes portion by computing the area that has been segmented out 
together adjusted by the distance from the camera.

## Depedencies
Food Portion Model uses MaskRCNN model to perform image segmentation
