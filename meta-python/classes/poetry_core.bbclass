inherit python_pep517 python3native setuptools3-base

DEPENDS += "python3-poetry-core-native"

poetry_core_do_configure () {
    :
}

# TODO: ideally this uses pypa/build
poetry_core_do_compile () {
    nativepython3 -c "from poetry.core.masonry import api; api.build_wheel('${PEP517_WHEEL_PATH}')"
}
do_compile[cleandirs] += "${PEP517_WHEEL_PATH}"

EXPORT_FUNCTIONS do_configure do_compile
