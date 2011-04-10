
def get_poppler_fpu_setting(bb, d):
	if bb.data.getVar('TARGET_FPU', d, 1) in [ 'soft' ]:
		return "--enable-fixedpoint"
	return ""

