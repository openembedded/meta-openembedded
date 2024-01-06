SUMMARY = "Vulkan/GL/GLES/EGL/GLX/WGL Loader-Generator based on the official specifications for multiple languages."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae570f26774ac096cff8f992091a223c"

SRC_URI = "git://github.com/Dav1dde/glad.git;protocol=https;branch=glad2"
SRCREV = "d08b1aa01f8fe57498f04d47b5fa8c48725be877"
S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
RDEPENDS:${PN} = "python3 python3-jinja2"
