# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

HOMEPAGE = "https://booting.oniroproject.org/distro/components/pim435"
SUMMARY = "A userspace driver application for PIM435 written in C"
DESCRIPTION = "A userspace driver application for PIM435 (Pimoroni LED matrix) \
written in C"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSES/MIT.txt;md5=7dda4e90ded66ab88b86f76169f28663"

SRC_URI = "git://booting.oniroproject.org/distro/components/pim435;protocol=https;branch=main"
SRCREV = "57214788a7b1e2288db624bf3087388946f93333"
S = "${WORKDIR}/git"

DEPENDS = "i2c-tools"

EXTRA_OEMAKE += "DESTDIR=${D}"

do_install() {
    oe_runmake install
}
