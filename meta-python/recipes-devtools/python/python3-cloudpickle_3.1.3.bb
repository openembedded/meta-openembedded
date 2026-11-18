SUMMARY = "Pickler class to extend the standard pickle.Pickler functionality"
DESCRIPTION = "cloudpickle makes it possible to serialize Python constructs \
not supported by the default pickle module from the Python standard library.\
\
cloudpickle is especially useful for cluster computing where Python code is \
shipped over the network to execute on remote hosts, possibly close to the \
data."
HOMEPAGE = "https://github.com/cloudpipe/cloudpickle"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b4d59aa5e2cc777722aac17841237931"

SRC_URI[sha256sum] = "cbe9fde14429a5db009ea05ce94e5182cf998c97b96918d50c10f73f9c2f4f33"

inherit pypi python_flit_core
