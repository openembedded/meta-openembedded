require llvm.inc
require llvm2.inc

PR = "r3"

SRC_URI += "file://0035-gcc-4.7.patch"

ARM_INSTRUCTION_SET = "arm"

# 0019-issue6065.patch is still needed but a bit modified, because it was resolved by
# http://llvm.org/viewvc/llvm-project/llvm/trunk/lib/Target/ARM/ARMJITInfo.cpp?r1=120304&r2=124694&pathrev=124694
# http://llvm.org/viewvc/llvm-project/llvm/trunk/lib/Target/ARM/ARMJITInfo.cpp?diff_format=h&r1=57911&r2=57910&pathrev=57911
# and still it fails with 
# {standard input}:31: Error: invalid register list to push/pop instruction -- `pop {r0,r1,r2,r3,lr}'
# make[2]: *** [lib/Target/ARM/CMakeFiles/LLVMARMCodeGen.dir/ARMJITInfo.cpp.o] Error 1
# SRC_URI += "file://0019-issue6065.patch"

SRC_URI_append_libc-uclibc = " file://arm_fenv_uclibc.patch "

SRC_URI[md5sum] = "793138412d2af2c7c7f54615f8943771"
SRC_URI[sha256sum] = "661236cfa17428b48cfa9cbb9909f7569c64b8ecd219fd91dbc00e3b557b3779"
