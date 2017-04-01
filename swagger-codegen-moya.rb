# Documentation: http://docs.brew.sh/Formula-Cookbook.html
#                http://www.rubydoc.info/github/Homebrew/brew/master/Formula
# PLEASE REMOVE ALL GENERATED COMMENTS BEFORE SUBMITTING YOUR PULL REQUEST!

class SwaggerCodegenMoya < Formula
  desc "Swagger Generator for Swift Moya "
  homepage "https://github.com/dangthaison91/swagger-codegen-moya/"
  url "https://github.com/dangthaison91/swagger-codegen-moya/archive/2.2.3.tar.gz"
  sha256 "828b0a66a31dd98ea5b0bbd1277116799c1dafb1d7e239f0405a5b1f36dda5e2"

  depends_on :java => "1.7+"
  depends_on "maven" => :build

  def install
      ENV.java_cache

    #   system "mvn", "clean", "package", "-Dmaven.test.skip=true"
      libexec.install "modules/swagger-codegen-cli/target/swagger-codegen-cli.jar"
      bin.write_jar_script libexec/"swagger-codegen-cli.jar", "swagger-codegen-moya"

    # Remove unrecognized options if warned by configure
    # system "./configure", "--disable-debug",
                        #   "--disable-dependency-tracking",
                        #   "--disable-silent-rules",
                        #   "--prefix=#{prefix}"
    # system "cmake", ".", *std_cmake_args
    # system "make", "install" # if this fails, try separate make/make install steps
  end

  test do
    # `test do` will create, run in and delete a temporary directory.
    #
    # This test will fail and we won't accept that! It's enough to just replace
    # "false" with the main program this formula installs, but it'd be nice if you
    # were more thorough. Run the test with `brew test swagger-codegen-moya`. Options passed
    # to `brew install` such as `--HEAD` also need to be provided to `brew test`.
    #
    # The installed folder is not in the path, so use the entire path to any
    # executables being tested: `system "#{bin}/program", "do", "something"`.
    system "false"
  end
end
