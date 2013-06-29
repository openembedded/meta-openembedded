# LLVM does not provide ABI stability between different versions. For this
# reason OE makes it possible to build and install different llvm versions
# at the same time.
#
# This is true for the normal recipes as well as the native ones.
#
# All regular installation directories are prefixed with 'llvm${LLVM_RELEASE}'
# e.g. "${STAGING_BINDIR}/llvm2.5" or "${STAGING_INCDIR}/llvm2.5"
#
# For your program or library that makes use of llvm you do should not need to
# modify anything as long as it uses the results of various llvm-config
# invocations. If you need customizations something is wrong and it needs to be
# fixed (report bug).
#
# However the *recipe* for your program/library *must* declare
# export WANT_LLVM_RELEASE = "<valid version number>"
# The version number is picked up by a generic wrapper script which just calls
# the variant of the specified version.

DESCRIPTION = "The Low Level Virtual Machine"
HOMEPAGE = "http://llvm.org"

# 3-clause BSD-like
# University of Illinois/NCSA Open Source License
LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=d0a3ef0d3e0e8f5cf59e5ffc273ab1f8"

# 2.* inherits also cmake
# 3.* inherits also automake
inherit perlnative pythonnative

LLVM_RELEASE = "${PV}"
LLVM_DIR = "llvm${LLVM_RELEASE}"

FILES_${PN}-dbg += "${bindir}/${LLVM_DIR}/.debug \
                    ${libdir}/${LLVM_DIR}/.debug \
"
FILES_${PN}-dev += "${bindir} \
                    ${libdir}/${LLVM_DIR}/BugpointPasses.so \
                    ${libdir}/${LLVM_DIR}/LLVMHello.so \
"
FILES_${PN}-staticdev = "${libdir}/${LLVM_DIR}/*.a"
