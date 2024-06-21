SUMMARY = "COCO is a large image dataset designed for object detection, segmentation, \
           person keypoints detection, stuff segmentation, and caption generation."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://pycocotools/coco.py;beginline=45;endline=45;md5=12cb539683cf245df8b6ce782d78f647"

inherit setuptools3 pypi

SRC_URI[sha256sum] = "8f2bcedb786ba26c367a3680f9c4eb5b2ad9dccb2b34eaeb205e0a021e1dfb8d"

DEPENDS = "python3-cython-native python3-numpy-native virtual/crypt"
RDEPENDS:${PN} = "python3-matplotlib python3-pillow python3-profile"
