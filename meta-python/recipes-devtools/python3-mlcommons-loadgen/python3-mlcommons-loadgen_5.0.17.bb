SUMMARY = "MLPerf Inference LoadGen python bindings"
DESCRIPTION = "The LoadGen is a reusable module that efficiently and fairly \
               measures the performance of inference systems. It generates \
               traffic for scenarios as formulated by a diverse set of experts \
               in the MLCommons working group, to emulate the workloads seen in \
               mobile devices, autonomous vehicles, robotics, and cloud-based \
               setups."
HOMEPAGE = "https://mlcommons.org/"
BUGTRACKER = "https://github.com/mlcommons/inference/issues"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://setup.py;beginline=1;endline=14;md5=2c6e34309ef8d57d59ce119f63bc1b76"

DEPENDS = "python3-pybind11-native"

inherit setuptools3 pypi

PYPI_PACKAGE = "mlcommons_loadgen"
UPSTREAM_CHECK_PYPI_PACKAGE = "mlcommons_loadgen"

SRC_URI[sha256sum] = "06410ee545d6959221c7b66e3f3c536c305b3a833dd5f691642f1764d556b990"

# Because the pyproject.toml contains invalid requirements.
INSANE_SKIP += "pep517-backend"
