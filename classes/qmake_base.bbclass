
OE_QMAKE_PLATFORM = "${TARGET_OS}-oe-g++"
QMAKESPEC = "${QMAKE_MKSPEC_PATH}/${OE_QMAKE_PLATFORM}"

# We override this completely to eliminate the -e normally passed in
EXTRA_OEMAKE = ' MAKEFLAGS= '

export OE_QMAKE_CC="${CC}"
export OE_QMAKE_CFLAGS="${CFLAGS}"
export OE_QMAKE_CXX="${CXX}"
export OE_QMAKE_LDFLAGS="${LDFLAGS}"
export OE_QMAKE_AR="${AR}"
export OE_QMAKE_STRIP="echo"
export OE_QMAKE_RPATH="-Wl,-rpath-link,"

# do not export STRIP to the environment
STRIP[unexport] = "1"

# default to qte2 via bb.conf, inherit qt3x11 to configure for qt3x11

oe_qmake_mkspecs () {
    mkdir -p mkspecs/${OE_QMAKE_PLATFORM}
    for f in ${QMAKE_MKSPEC_PATH}/${OE_QMAKE_PLATFORM}/*; do
        if [ -L $f ]; then
            lnk=`readlink $f`
            if [ -f mkspecs/${OE_QMAKE_PLATFORM}/$lnk ]; then
                ln -s $lnk mkspecs/${OE_QMAKE_PLATFORM}/`basename $f`
            else
                cp $f mkspecs/${OE_QMAKE_PLATFORM}/
            fi
        else
            cp $f mkspecs/${OE_QMAKE_PLATFORM}/
        fi
    done
}

qmake_base_do_configure() {
	case ${QMAKESPEC} in
	*linux-oe-g++|*linux-uclibc-oe-g++|*linux-gnueabi-oe-g++|*linux-uclibceabi-oe-g++)
		;;
	*-oe-g++)
		die Unsupported target ${TARGET_OS} for oe-g++ qmake spec
		;;
	*)
		oenote Searching for qmake spec file
		paths="${QMAKE_MKSPEC_PATH}/qws/${TARGET_OS}-${TARGET_ARCH}-g++"
		paths="${QMAKE_MKSPEC_PATH}/${TARGET_OS}-g++ $paths"

		if (echo "${TARGET_ARCH}"|grep -q 'i.86'); then
			paths="${QMAKE_MKSPEC_PATH}/qws/${TARGET_OS}-x86-g++ $paths"
		fi
		for i in $paths; do
			if test -e $i; then
				export QMAKESPEC=$i
				break
			fi
		done
		;;
	esac

	oenote "using qmake spec in ${QMAKESPEC}, using profiles '${QMAKE_PROFILES}'"

	if [ -z "${QMAKE_PROFILES}" ]; then 
		PROFILES="`ls *.pro`"
	else
		PROFILES="${QMAKE_PROFILES}"
	fi

	if [ -z "$PROFILES" ]; then
		die "QMAKE_PROFILES not set and no profiles found in $PWD"
        fi

	if [ ! -z "${EXTRA_QMAKEVARS_POST}" ]; then
		AFTER="-after"
		QMAKE_VARSUBST_POST="${EXTRA_QMAKEVARS_POST}"
		oenote "qmake postvar substitution: ${EXTRA_QMAKEVARS_POST}"
	fi

	if [ ! -z "${EXTRA_QMAKEVARS_PRE}" ]; then
		QMAKE_VARSUBST_PRE="${EXTRA_QMAKEVARS_PRE}"
		oenote "qmake prevar substitution: ${EXTRA_QMAKEVARS_PRE}"
	fi

#oenote "Calling '${OE_QMAKE_QMAKE} -makefile -spec ${QMAKESPEC} -o Makefile $QMAKE_VARSUBST_PRE $AFTER $PROFILES $QMAKE_VARSUBST_POST'"
	unset QMAKESPEC || true
	${OE_QMAKE_QMAKE} -makefile -spec ${QMAKESPEC} -o Makefile $QMAKE_VARSUBST_PRE $AFTER $PROFILES $QMAKE_VARSUBST_POST || die "Error calling ${OE_QMAKE_QMAKE} on $PROFILES"
}

EXPORT_FUNCTIONS do_configure

addtask configure after do_unpack do_patch before do_compile
