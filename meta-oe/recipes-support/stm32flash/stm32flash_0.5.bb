SUMMARY = "Open source flash program for STM32 using the ST serial bootloader"
HOMEPAGE = "https://sourceforge.net/projects/stm32flash/"
BUGTRACKER = "https://sourceforge.net/p/stm32flash/tickets/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools-brokensep

S = "${WORKDIR}/${PN}"

SRC_URI = "https://prdownloads.sourceforge.net/stm32flash/stm32flash-${PV}.tar.gz"

SRC_URI[md5sum] = "40f673502949f3bb655d2bcc539d7b6a"
SRC_URI[sha256sum] = "97aa9422ef02e82f7da9039329e21a437decf972cb3919ad817f70ac9a49e306"

FILES_${PN} = "/usr/local/bin/stm32flash"
