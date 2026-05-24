
### Run Plugin
```shell
./gradlew runIde
```
<p>
1. A second IntelliJ instance opens.
2. View -> Tool Windows -> AI Agent
</p>

### Generate Plugin ZIP
```shell
./gradlew buildPlugin
```
<p>
The generated ZIP file is located in the `build/distributions` directory.
</p>

### Install Plugin from ZIP
<p>
1. Open IntelliJ IDEA.
2. Go to `File` -> `Settings` (or `IntelliJ IDEA` -> `Preferences` on macOS).
3. Navigate to `Plugins`.
4. Click on the gear icon and select `Install Plugin from Disk`.
5. Choose the generated ZIP file from the `build/distributions` directory.
6. Restart IntelliJ IDEA to activate the plugin.
</p>


### Publish to Marketplace
```shell
./gradlew publishPlugin
```
<p>
1. create account in <a href="https://plugins.jetbrains.com/?utm_source=chatgpt.com">JetBrains Marketplace</a>
2. Profile -> My Tokens
3. Add Publish Token in gradle.properties
    intellijPlatformPublishingToken=YOUR_TOKEN
4. Run the command above to publish the plugin to the marketplace after approval
5. After approval, the plugin will be available on the marketplace for users to download and install.
6. you can install through Settings -> Plugins -> Marketplace
</p>