
def get_navit_fpu_setting(bb, d):
	if bb.data.getVar('TARGET_FPU', d, 1) in [ 'soft' ]:
		return "--enable-avoid-float"
	return ""

