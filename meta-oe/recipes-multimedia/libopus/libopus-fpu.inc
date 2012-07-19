
def get_libopus_fpu_setting(bb, d):
    if d.getVar('TARGET_FPU', True) in [ 'soft' ]:
        return "--enable-fixed-point"
    return ""

