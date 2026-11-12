SUMMARY = "Perfetto - System profiling, app tracing and trace analysis."
HOMEPAGE = "https://github.com/google/perfetto"

# Add only patches here which are used by both perfetto and libperfetto
SRC_URI = "git://github.com/google/perfetto.git;protocol=https;name=perfetto;nobranch=1 \
	"

SRCREV_perfetto = "1e9c61e9844affd7dccd3d42d9ea9f3a3d10cf6d"
SRCREV_FORMAT = "perfetto"
PV = "59.1"
