SUMMARY = "Advanced Python Scheduler (APScheduler) is a Python library that lets you schedule your Python code to be executed later, either just once or periodically."
HOMEPAGE = "https://github.com/agronholm/apscheduler"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f0e423eea5c91e7aa21bdb70184b3e53"

SRC_URI[sha256sum] = "cd2fcc9330039a81a5893472ad49facf23a6d5604cbe1d918c835c6de7834d5a"

PYPI_PACKAGE_SDIST = "apscheduler"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"
