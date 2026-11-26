project(
  'libperfetto-c',
  ['cpp'],
  version : files('VERSION'),
  license : 'Apache-2.0',
  default_options : [
    'cpp_std=c++17',
    'warning_level=1',
  ],
)

mapfile = join_paths(meson.current_source_dir(), 'libperfetto_c.map')

thread_dep = dependency('threads')

cpp = meson.get_compiler('cpp')

perfetto_c_cpp_args = []
perfetto_c_cpp_args += cpp.get_supported_arguments([
  '-ffunction-sections',
  '-fdata-sections',
  '-fno-exceptions',
  '-fno-rtti',
])

perfetto_c_link_args = []
perfetto_c_link_args += cpp.get_supported_link_arguments([
  '-static-libstdc++',
  '-Wl,--exclude-libs,ALL',
  '-Wl,--gc-sections',
  '-Wl,--icf=all',
])

perfetto_c = shared_library(
  'perfetto_c',
  'perfetto_c.cc',
  dependencies : [thread_dep],
  install : true,
  version : meson.project_version(),
  gnu_symbol_visibility : 'inlineshidden',
  cpp_args : [
    perfetto_c_cpp_args,
    '-DPERFETTO_SDK_SHLIB_IMPLEMENTATION',
  ],
  link_args: [
    perfetto_c_link_args,
    '-Wl,--version-script=' + mapfile,
  ],
  link_depends : mapfile,
)

install_headers('perfetto_c.h')

pkg = import('pkgconfig')
pkg.generate(
  perfetto_c,
  name : 'libperfetto_c',
  filebase : 'libperfetto_c',
  description : 'Perfetto C SDK shared library',
  version : meson.project_version(),
  extra_cflags : ['-DPERFETTO_SDK_SHLIB'],
)
