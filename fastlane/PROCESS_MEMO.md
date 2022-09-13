# 로컬 환경에서 fastlane 테스트
## ruby 설치 명령어를 ~/.zshrc 에 추가했다면,
```
$ source ~/.zshrc
```
## fastlane 설치
```
$ gem install fastlane
```
## fastlane 초기화
```
(이건 iOS 전용인가?)
- fastlane 디렉토리 생성 
- 그 안에서 아래 명령어 입력
$ fastlane init

[✔] 🚀 
[✔] Looking for iOS and Android projects in current directory...
[03:27:30]: Created new folder './fastlane'.
[03:27:30]: No iOS or Android projects were found in directory '/Users/byungyongpark/Desktop/d/android-workspace/architecture-samples/fastlane'
[03:27:30]: Make sure to `cd` into the directory containing your iOS or Android app
[03:27:30]: Alternatively, would you like to manually setup a fastlane config in the current directory instead? (y/n) y
```
## fastlane 디렉토리가 하나 더 생기면, 그 안의 Appfile, Fastfile을 상위 fastlane으로 이동
## 마지막에 생성된 fastlane 디렉토리, Gemfile, Gemfile.lock 파일 제거

## fastlane unit test 명령어
```sh
$ bundle exec fastlane custom_lane
or
$ fastlane android custom_lane
```