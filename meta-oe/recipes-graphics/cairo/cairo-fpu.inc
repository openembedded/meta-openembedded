
def get_cairo_fpu_setting(bb, d):
	if bb.data.getVar('TARGET_FPU', d, 1) in [ 'soft' ]:
		return "--disable-some-floating-point"
	return ""

