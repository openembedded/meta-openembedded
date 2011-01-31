inherit siteinfo

ARM_INSTRUCTION_SET = "arm"

def qt_arch(d):
    import bb, re
    arch = bb.data.getVar('TARGET_ARCH', d, 1)
    if re.match("^i.86$", arch):
        arch = "i386"
    elif re.match("^arm.*", arch):
        arch = "arm"
    elif arch == "x86_64":
        arch = "x86"
    elif arch == "mipsel":
        arch = "mips"
    return arch

def qt_endian(d):
    import bb
    if bb.data.getVar('SITEINFO_ENDIANESS', d, True) == "le":
        return "-little-endian"
    elif bb.data.getVar('SITEINFO_ENDIANESS', d, True) == "be":
        return "-big-endian"
    else:
        assert False
