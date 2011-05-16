# anonymous support class from angstrom
# 
# Features:
#
# * blacklist handling, set ANGSTROM_BLACKLIST_pn-blah = "message"
#

python () {
    import bb

    blacklist = bb.data.getVar("ANGSTROM_BLACKLIST", d, 1)
    pkgnm = bb.data.getVar("PN", d, 1)
    distro = bb.data.getVar("DISTRO", d, 1)

    if blacklist:
	bb.note("%s DOES NOT support %s because %s" % (distro,pkgnm, blacklist))
        raise bb.parse.SkipPackage("%s DOES NOT support %s because %s" % (distro,pkgnm, blacklist))

}

