SUMMARY = "COCO is a large image dataset designed for object detection, segmentation, \
           person keypoints detection, stuff segmentation, and caption generation."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://pycocotools/coco.py;beginline=45;endline=45;md5=12cb539683cf245df8b6ce782d78f647"

inherit pypi python_setuptools_build_meta cython


SRC_URI[sha256sum] = "34254d76da85576fcaf5c1f3aa9aae16b8cb15418334ba4283b800796bd1993d"

DEPENDS = "python3-numpy-native virtual/crypt python3-wheel-native"
RDEPENDS:${PN} = "python3-matplotlib python3-pillow python3-profile"
