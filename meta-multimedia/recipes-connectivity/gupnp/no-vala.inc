do_configure_prepend () {
    # Vala needs gobject-introspection so won't work. Instead of depending
    # on vala-native just to run configure, comment out the Vala macro.
    sed -i -e 's/\(^[ \t]*VALA_PROG_VAPIGEN\)/dnl \1/g' ${S}/configure.ac
}
