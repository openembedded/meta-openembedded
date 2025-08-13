DESCRIPTION = "POSIX IPC primitives (semaphores, shared memory and message queues) for Python"
HOMEPAGE = "https://semanchuk.com/philip/posix_ipc/"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3d8df223c2614dbf1aabdc1ca23cc10"

PYPI_PACKAGE = "posix_ipc"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

SRC_URI[sha256sum] = "6e559ac5bb5f6f233c396103f4868e383bbd8f4e54d20876910896f47d353448"

SRC_URI += " \
    file://0001-build_support-fix-cross-compilation-error-when-CC-is.patch \
    "

# Message queue support requires librt for proper linking
LDFLAGS += "-lrt"

inherit pypi python_setuptools_build_meta
