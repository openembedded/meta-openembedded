SUMMARY = "low-footprint drop-in polkit replacement"
HOMEPAGE = "https://github.com/bmwcarit/smolkit"
DESCRIPTION = "\
  Service that implements a subset of polkit's D-Bus interface with a static configuration language. \
"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit cmake features_check pkgconfig

DEPENDS = "\
  rapidjson \
  sdbus-c++ \
"

CONFLICT_DISTRO_FEATURES = "polkit"
REQUIRED_DISTRO_FEATURES = "smolkit"

PV = "0.2+git"

SRC_URI = "\
    git://github.com/bmwcarit/${BPN}.git;protocol=https;branch=main \
"
SRCREV = "46bbfd5c922a8d03a534398ee129ded822dfcafe"

S = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}"
