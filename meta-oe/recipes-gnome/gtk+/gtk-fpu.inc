
def get_gtk_fpu_setting(bb, d):
	if bb.data.getVar('TARGET_FPU', d, 1) in [ 'soft' ]:
		return "--enable-integer-pixops"
	return ""

