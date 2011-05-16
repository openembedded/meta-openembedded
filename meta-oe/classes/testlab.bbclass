#
# Performs various tests and analysises on images
#
# Copyright (C) 2007, 2008 Koen Kooi <koen@openembedded.org> 

# The current features are:
# 1) dump a list of installed packages
# 2) dump a list of sizes of installed packages
# 3) dependency graphs of installed packages

# See 
#  * http://dominion.thruhere.net/koen/cms/the-testlab-strikes-again
#  * http://dominion.thruhere.net/koen/cms/package-relations-inside-images
#  for use cases

# TODO: 
# * log information to a server for safekeeping
# * use git notes to record this info into the scm
# * add test suite to run on the target device 


# Needs 'dot', 'opkg-cl'

do_testlab() {
if [ -e  ${IMAGE_ROOTFS}/etc/opkg ] && [ "${ONLINE_PACKAGE_MANAGEMENT}" = "full" ] ; then

	IPKG_TMP_DIR="${IMAGE_ROOTFS}-tmp"
	IPKG_ARGS="-f ${STAGING_ETCDIR_NATIVE}/opkg.conf -o ${IMAGE_ROOTFS} -t ${IPKG_TMP_DIR}"

	TESTLAB_DIR="${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}-testlab"
        mkdir -p ${TESTLAB_DIR}/
        mkdir -p ${IPKG_TMP_DIR}/
	ls -laR ${IMAGE_ROOTFS} > ${TESTLAB_DIR}/files-in-image.txt 	
     
	echo > ${TESTLAB_DIR}/installed-packages.txt
	echo -e "digraph depends {\n    node [shape=plaintext]" > ${TESTLAB_DIR}/depends.dot

	for pkg in $(opkg-cl ${IPKG_ARGS} list_installed | awk '{print $1}') ; do 
		opkg-cl ${IPKG_ARGS} info $pkg | grep -B 7 -A 7 "^Status.* \(\(installed\)\|\(unpacked\)\)" | awk '/^Package/ {printf $2"_"} /^Version/ {printf $2"_"} /^Archi/ {print $2".ipk"}'  >> ${TESTLAB_DIR}/installed-packages.txt

    		for depends in $(opkg-cl ${IPKG_ARGS} info $pkg | grep ^Depends) ; do 
        		echo "$pkg OPP $depends;" | grep -v "(" | grep -v ")" | grep -v "$pkg OPP Depends" | sed -e 's:,::g' -e 's:-:_:g' -e 's:\.:_:g' -e 's:+::g' |sed 's:OPP:->:g' >> ${TESTLAB_DIR}/depends.dot
    		done
    		
		for recommends in $(opkg-cl ${IPKG_ARGS} info $pkg | grep ^Recom) ; do
        		echo "$pkg OPP $recommends [style=dotted];" | grep -v "(" | grep -v ")" | grep -v "$pkg OPP Recom" | sed -e 's:,::g' -e 's:-:_:g' -e 's:\.:_:g' -e 's:+::g' |sed 's:OPP:->:g' >> ${TESTLAB_DIR}/depends.dot
    		done
	done

	echo "}" >>  ${TESTLAB_DIR}/depends.dot
	rm -rf ${IPKG_TMP_DIR}
	
	grep -v kernel_2 ${TESTLAB_DIR}/depends.dot | grep -v kernel_image > ${TESTLAB_DIR}/depends-nokernel.dot
	grep -v libc6 ${TESTLAB_DIR}/depends-nokernel.dot | grep -v libgcc > ${TESTLAB_DIR}/depends-nokernel-nolibc.dot
	grep -v update_ ${TESTLAB_DIR}/depends-nokernel-nolibc.dot > ${TESTLAB_DIR}/depends-nokernel-nolibc-noupdate.dot
        grep -v kernel_module ${TESTLAB_DIR}/depends-nokernel-nolibc-noupdate.dot > ${TESTLAB_DIR}/depends-nokernel-nolibc-noupdate-nomodules.dot

	#dot has some library troubles when run under fakeroot, uncomment at your own risk
	#dot -Tpng -o ${TESTLAB_DIR}/image-dependencies.png  ${TESTLAB_DIR}/depends.dot
	#dot -Tpng -o ${TESTLAB_DIR}/image-dependencies-nokernel-nolibc.png ${TESTLAB_DIR}/depends-nokernel-nolibc.dot
	#dot -Tpng -o ${TESTLAB_DIR}/image-dependencies-nokernel-nolibc-noupdate.png ${TESTLAB_DIR}/depends-nokernel-nolibc-noupdate.dot
	#dot -Tpng -o ${TESTLAB_DIR}/image-dependencies-nokernel-nolibc-noupdate-nomodules.png ${TESTLAB_DIR}/depends-nokernel-nolibc-noupdate-nomodules.dot

	for file in $(cat ${TESTLAB_DIR}/installed-packages.txt) ; do 
     		du -k $(find ${DEPLOY_DIR_IPK} -name "$file") | head -n1
	done | grep "\.ipk" | sed -e s:${DEPLOY_DIR_IPK}::g | sort -n -r | awk '{print $1 "\tKiB " $2}' > ${TESTLAB_DIR}/installed-package-sizes.txt

	# Log results to a git controlled directory structure than can be pushed to a remote location
	if [ "${TESTLABLOG}" = "remote" ] && [ -n "${TESTLABREMOTEDIR}" ] ; then
		TESTLABLOGDIR="${MACHINE_ARCH}/${IMAGE_BASENAME}"
		mkdir -p ${TESTLABREMOTEDIR}/${TESTLABLOGDIR}
		cp ${TESTLAB_DIR}/*package* ${TESTLAB_DIR}/depends.dot ${TESTLABREMOTEDIR}/${TESTLABLOGDIR}
		# force change to record builds where the testlab contents didn't change, but other things (e.g. git rev) did
		echo "${MACHINE}: ${IMAGE_BASENAME} configured for ${DISTRO} ${DISTRO_VERSION} using branch ${METADATA_BRANCH} and revision ${METADATA_REVISION} " > ${TESTLABREMOTEDIR}/${TESTLABLOGDIR}/build-id
		# This runs inside fakeroot, so the git author is listed as root (or whatever root configured it to be) :(
		( cd ${TESTLABREMOTEDIR}/
		  git add ${TESTLABLOGDIR}/*
		  git commit ${TESTLABLOGDIR}/ -m "${MACHINE}: ${IMAGE_BASENAME} configured for ${DISTRO} ${DISTRO_VERSION} using branch ${METADATA_BRANCH} and revision ${METADATA_REVISION}" --author "testlab <testlab@${DISTRO}>" || true)
	fi
fi
}

IMAGE_POSTPROCESS_COMMAND += "  do_testlab ;"
