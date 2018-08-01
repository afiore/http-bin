pkg_name=http-bin
pkg_origin=afiore
pkg_version="0.1.0"
pkg_maintainer="Andrea Fiore <andrea.giulio.fiore@gmail.com>"
pkg_license=("Apache-2.0")
pkg_source="http://github.com/afiore/${pkg_name}/archive/${pkg_version}.tar.gz"
pkg_filename="${pkg_name}-${pkg_version}.tar.gz"
pkg_shasum="e7faae36972683d30ef4c2523f4825c30237e59879d345c607bf1fd3a5fb85a5"


pkg_build_deps=(
  core/sbt
)

pkg_deps=(
  core/redis
  core/jre8
)

pkg_binds=(
  [cache]="port"
)

pkg_upstream_url="https://github.com/afiore/http-bin"

do_build() {
  sbt assembly
}

do_install() {
  artefact=`find $HAB_CACHE_SRC_PATH -name '*.jar'`
  cp $artefact $pkg_prefix/httpbin.jar
}
