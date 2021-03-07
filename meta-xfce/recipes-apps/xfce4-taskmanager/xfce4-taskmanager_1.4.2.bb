SUMMARY = "Easy to use task manager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit xfce-app

DEPENDS += "gtk+3 cairo libwnck libxmu xfce4-dev-tools-native"

SRC_URI[sha256sum] = "cd122240e1c195e4e6f159da1bb86b38c41ba27892399ad85a18c37ea20e48f9"
