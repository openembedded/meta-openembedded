SUMMARY = "A MutableSet that remembers its order, so that every entry has an index."
HOMEPAGE = "http://github.com/LuminosoInsight/ordered-set"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://MIT-LICENSE;md5=2b36be0d99854aa2ae292a800a7c1d4e"

SRC_URI[md5sum] = "c8f5b99b717618803b914dd9cfe5ef43"
SRC_URI[sha256sum] = "a34399fe6aa78358aaa00129d67c65b4aa099adfc023731b1d756c85776a89bb"

inherit pypi setuptools3

DEPENDS += "python3-pytest-runner-native"
