# CountryCodes.rb

A JRuby wrapper for the country-codes library.

## Building
* Install Bundler 
  ```shell
  gem install bundler
  ```
* Install gem dependencies 
  ```shell
  bundle install
  ```
* Configure an access token for Github Packages Maven repo:
  ```shell
  GITHUB_USER=<username> GITHUB_TOKEN=<token> envsubst < settings.xml.template > settings.xml
  ```
* Install jar dependencies
  ```shell
  bundle exec rake jars:install
  ```
* Now you can just build the gem!
  ```shell
  gem build
  ```

There's also an option to just run `bin/setup` shell file, which just executes commands listed above (except the last one).
Note that `$GITHUB_USER` and `$GITUHB_TOKEN` env variables should be set to valid GitHub credentials.
