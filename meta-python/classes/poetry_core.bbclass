inherit pip_install_wheel python3native python3-dir setuptools3-base

DEPENDS += "python3 python3-poetry-core-native python3-pip-native"

do_configure () {
    mkdir -p ${S}/dist
    cat > ${S}/build-it.py << EOF
from poetry.core.masonry import api
api.build_wheel('${S}/dist')
EOF
}

do_compile () {
    ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} ${S}/build-it.py
}
