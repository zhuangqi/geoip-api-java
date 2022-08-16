# geoip-api-java
基于GeoLite2免费数据提供IP查询接口服务

## Register MAXMIND ACCOUNT ##
https://www.maxmind.com/en/account/login

## GET License Keys
注册完账户后，在Account - Manage License Keys 创建许可证密钥


## Configuring
替换docker-compose.yml中的配置
* `GEOIPUPDATE_ACCOUNT_ID` - Your MaxMind account ID.
* `GEOIPUPDATE_LICENSE_KEY` - Your case-sensitive MaxMind license key.

## Running ##
docker-compose up -d 

How to `curl`:

```bash
curl -X POST http://localhost:8081/qryip?ip=85.25.43.84
```