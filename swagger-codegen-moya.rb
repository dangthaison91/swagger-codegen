# Documentation: http://docs.brew.sh/Formula-Cookbook.html
#                http://www.rubydoc.info/github/Homebrew/brew/master/Formula
# PLEASE REMOVE ALL GENERATED COMMENTS BEFORE SUBMITTING YOUR PULL REQUEST!

class SwaggerCodegenMoya < Formula
  desc "Swagger Generator for Swift Moya "
  homepage "https://github.com/dangthaison91/swagger-codegen-moya/"
  url "https://github.com/dangthaison91/swagger-codegen-moya/raw/swift3_moya/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar"
  # sha256 "65a9d0e0a80528f0697851b2b81934bdbcfae2370725518755da2d85c63d5a93"

  depends_on :java => "1.7+"
  # depends_on "maven" => :build

  def install
    #   ENV.java_cache
    #   system "mvn", "clean", "package", "-Dmaven.test.skip=true"
    libexec.install "swagger-codegen-cli.jar"
    bin.write_jar_script libexec/"swagger-codegen-cli.jar", "swagger-codegen-moya"
  end
end
