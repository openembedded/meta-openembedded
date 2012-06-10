PRINC := "${@int(PRINC) + 4}"

POLKITAUTH ??= "consolekit"

PACKAGECONFIG += "${POLKITAUTH}"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
# there is no --enable/--disable option for consolekit and it's not picked by shlibs, so add it to RDEPENDS
PACKAGECONFIG[consolekit] = ",,,consolekit"
