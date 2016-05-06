# Copyright (C) 2014-2015 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SUMMARY = "Set of Bluetooh related tools for inclusion on images"
DESCRIPTION = "Add bluetooth tools based on the version of BlueZ in use.\
The tools that have been tested and work the best are pulled in \
automatically.  The same packagegroup can be used in a recipe without \
the need to know which version of BlueZ is in use. \
Supports BlueZ4 and BlueZ5."

inherit packagegroup

BLUEZ4_INSTALL = " \
    obexftp \
"

BLUEZ5_INSTALL = " \
     bluez5-noinst-tools \
     bluez5-obex \
     bluez5-testtools  \
     libasound-module-bluez \
     ${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', "pulseaudio-module-bluetooth-discover \
                                                            pulseaudio-module-bluetooth-policy \
                                                            pulseaudio-module-bluez5-discover \
                                                            pulseaudio-module-bluez5-device \
                                                            pulseaudio-module-switch-on-connect \
                                                            pulseaudio-module-loopback", \
                                             '', d)} \
"

# Install either bluez4 or bluez5 if they are in distro.  
# Otherwise install nothing.
RDEPENDS_${PN} = " \
     ${@bb.utils.contains('DISTRO_FEATURES', 'bluez5', '${BLUEZ5_INSTALL}', "", d)} \
     ${@bb.utils.contains('DISTRO_FEATURES', 'bluez4', '${BLUEZ4_INSTALL}', "", d)} \
"
