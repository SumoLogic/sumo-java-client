1. Make sure you have all credentials - access to `Open Source` vault in 1Password.
    1. Can login as `sumoapi` https://oss.sonatype.org/index.html
    2. Can import and verify the signing key:
        ```
        gpg --import ~/Desktop/api.private.key
        gpg-agent --daemon
        touch a
        gpg --use-agent --sign a
        gpg -k
        ```
    3. Have nexus and signing credentials in `~/.gradle/gradle.properties`
        ```
        nexus_username=sumoapi
        nexus_password=${sumoapi_password_for_sonatype_nexus}
        signing.gnupg.executable=gpg
        signing.gnupg.keyName=${id_of_imported_sumoapi_key}
        signing.gnupg.passphrase=${password_for_imported_sumoapi_key}
        ```
2. Remove `-SNAPSHOT` suffix from `version` in `build.gradle`
3. Make a release branch with Scala version and project version, ex. `sumo-java-client-2.13`:
    ```
    export RELEASE_VERSION=sumo-java-client-2.13
    git checkout -b ${RELEASE_VERSION}
    git add build.gradle
    git commit -m "[release] ${RELEASE_VERSION}"
    ```
4. Perform a release:
    ```
    ./gradlew build publish
    ```
5. Go to https://oss.sonatype.org/index.html#stagingRepositories, search for com.sumologic, close and release your repo.
   NOTE: If you had to login, reload the URL. It doesn't take you to the right page post-login
6. Update the `README.md` and `CHANGELOG.md` with the new version and set upcoming snapshot `version`
   in `build.gradle`, ex. `2.14-SNAPSHOT`
7. Commit the change and push as a PR:
    ```
    git add build.gradle README.md CHANGELOG.md
    git commit -m "[release] Updating version after release ${RELEASE_VERSION}"
    git push
    ```
