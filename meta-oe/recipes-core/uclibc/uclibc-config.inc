#
# Set the ARCH environment variable for uClibc compilation.
# Return value must match one of the architectures known to uClibc:
# libc/sysdeps/*/*
#

valid_archs = "\
alpha \
arm \
avr32 \
bfin \
cris \
e1 \
frv \
h8300 \
hppa \
i386 \
i960 \
ia64 \
m68k \
microblaze \
mips \
nios \
nios2 \
powerpc \
sh \
sh64 \
sparc \
v850 \
vax \
x86_64 \
xtensa \
"
def map_uclibc_arch(a, d):
	"""Return the uClibc architecture for the given TARGET_ARCH."""
	import re

	valid_archs = bb.data.getVar('valid_archs', d, 1).split()

	if   re.match('^(arm|sa110).*', a):	return 'arm'
	elif re.match('^(i.86|athlon)$', a):	return 'i386'
	elif re.match('^mips.*', a):		return 'mips'
	elif re.match('^parisc.*', a):		return 'hppa'
	elif re.match('^ppc.*', a):		return 'powerpc'
	elif re.match('^s390.*', a):		return 's390'
	elif re.match('^sh.*', a):		return 'sh'
	elif re.match('^(sun|sparc).*', a):	return 'sparc'
	elif re.match('^xtensa.*', a):		return 'xtensa'
        elif a in valid_archs:			return a
	else:
		bb.error("cannot map '%s' to a uClibc architecture" % a)

export UCLIBC_ARCH = "${@map_uclibc_arch(bb.data.getVar('TARGET_ARCH', d, 1), d)}"

def map_uclibc_abi(o, d):
	"""Return the uClibc ABI for the given TARGET_OS."""
	import re

	arch = bb.data.getVar('TARGET_ARCH', d, 1)
	if   map_uclibc_arch(bb.data.getVar('TARGET_ARCH', d, 1), d) == "arm":
		if re.match('.*eabi$', o): return 'ARM_EABI'
		else:                    return 'ARM_OABI'
	# FIXME: This is inaccurate! Handle o32, n32, n64
	elif re.match('^mips.*64$', arch):  return 'MIPS_N64_ABI'
	elif re.match('^mips.*', arch):    return 'MIPS_O32_ABI'
	return ""

export UCLIBC_ABI = "${@map_uclibc_abi(bb.data.getVar('TARGET_OS', d, 1), d)}"

def map_uclibc_endian(a, d):
	"""Return the uClibc endianess for the given TARGET_ARCH."""
	import re

	# Always BE
	if re.match('^(avr32|e1|frv|(parisc|hppa)|m68k|microblaze|powerpc.*|(sparc|sun).*)$', a):
		return 'BIG'
	# Possibly BE
	elif re.match('^((arm|sa110|arm.*eb)|h8300.*eb|(parisc|hppa).*eb|mips|sh.*eb|xtensa.*eb)$', a):
		return 'BIG'
	return 'LITTLE'

export UCLIBC_ENDIAN = "${@map_uclibc_endian(bb.data.getVar('TARGET_ARCH', d, 1), d)}"

# internal helper
def uclibc_cfg(feature, features, tokens, cnf, rem):
	if type(tokens) == type(""):
		tokens = [tokens]
	rem.extend(['/^[# ]*' + token + '[ =]/d' for token in tokens])
	if type(features) == type([]) and feature in features:
		cnf.extend([token + '=y' for token in tokens])
	else:
		cnf.extend(['# ' + token + ' is not set' for token in tokens])

# Map distro and machine features to config settings
def features_to_uclibc_settings(d):
	cnf, rem = ([], [])
	distro_features = bb.data.getVar('DISTRO_FEATURES', d, True).split()
	machine_features = bb.data.getVar('MACHINE_FEATURES', d, True).split()
	uclibc_cfg('ipv4',      distro_features, 'UCLIBC_HAS_IPV4', cnf, rem)
	uclibc_cfg('ipv6',      distro_features, 'UCLIBC_HAS_IPV6', cnf, rem)
	uclibc_cfg('largefile', distro_features, 'UCLIBC_HAS_LFS', cnf, rem)
	uclibc_cfg('nls',       distro_features, 'UCLIBC_HAS_LOCALE', cnf, rem)
	uclibc_cfg('thumb-interwork', distro_features,'USE_BX', cnf, rem)
	uclibc_cfg('xattr',     distro_features, 'UCLIBC_HAS_XATTR', cnf, rem)
	uclibc_cfg('ssp',       distro_features, 'UCLIBC_HAS_SSP', cnf, rem)
	uclibc_cfg('argp',       distro_features, 'UCLIBC_HAS_ARGP', cnf, rem)
	uclibc_cfg('kernel24',  machine_features,'UCLIBC_LINUX_MODULE_24', cnf, rem)
	return "\n".join(cnf), "\n".join(rem)
# X, Y = ${@features_to_uclibc_settings(d)}
# unfortunately doesn't seem to work with bitbake, workaround:
def features_to_uclibc_conf(d):
	cnf, rem = features_to_uclibc_settings(d)
	return cnf
def features_to_uclibc_del(d):
	cnf, rem = features_to_uclibc_settings(d)
	return rem
