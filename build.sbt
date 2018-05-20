val username = "lbqds"
val repo = "rlp"

organization := s"com.github.$username"
name := "rlp"
scalaVersion := "2.12.4"
version := "0.1"

libraryDependencies ++= Seq(
  "com.madgag.spongycastle" % "core" % "1.56.0.0",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint:unsound-match",
  "-Ywarn-inaccessible",
  "-Ywarn-unused-import",
  "-encoding", "utf-8"
)

scalacOptions in (Compile, console) ~= (_.filterNot(Set(
  "-Ywarn-unused-import",
  "-Xfatal-warnings"
)))

lazy val publishSettings = Seq(
  homepage := Some(url(s"https://github.com/$username/$repo")),
  licenses += "MIT" -> url(s"https://github.com/$username/$repo/blob/master/LICENSE"),
  scmInfo := Some(ScmInfo(url(s"https://github.com/$username/$repo"), s"git@github.com:$username/$repo.git")),
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  developers := List(
    Developer(id = username,
              name = "lbqds",
              email = "lbqds@outlook.com",
              url = new URL(s"http://github.com/$username"))
  ),
  publishMavenStyle := true,
  publishArtifact in Test := false,
  publishTo := Some(if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging),
  credentials ++= (for {
    username <- sys.env.get("SONATYPE_USERNAME")
    password <- sys.env.get("SONATYPE_PASSWORD")
  } yield Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", username, password)).toSeq
)
