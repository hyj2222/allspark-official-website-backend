# AllSpark 工作室官网 · 后端

AllSpark 工作室官网后端服务：负责报名数据收集与运营端管理，向前端提供数据接口。

## 技术栈
- Java 8 / Spring Boot 2.6.13 / MyBatis-Plus 3.5.2
- MySQL 8 / Druid 连接池
- Knife4j(OpenAPI2) 在线接口文档
- EasyExcel 报名数据导出

## 目录结构
- `controller`：接口层（报名、运营端登录、报名数据管理）
- `service`：业务层
- `mapper` + `resources/mapper`：数据访问层
- `model`：实体 / dto / vo
- `config`：分页插件、登录拦截器、跨域配置
- `common`：统一返回、错误码、密码工具
- `doc/db.sql`：数据库初始化脚本

## 快速启动

### 1. 初始化数据库（只需一次）
确保本机 MySQL 8 已启动，执行 `doc/db.sql`：

```bash
mysql -uroot -p < doc/db.sql
```

脚本会创建：数据库 `allspark`、报名表 `user`、管理员表 `admin_user`，并写入默认管理员。

### 2. 配置数据库连接（可选）
`src/main/resources/application-prod.yaml` 中默认账号为 `allspark / allspark520`，按需修改。

### 3. 编译打包
```bash
mvn clean package -DskipTests
```

### 4. 启动
```bash
java -jar target/allspark-official-website-0.0.1-SNAPSHOT.war --spring.profiles.active=prod
```

或在 IDEA 中直接运行 `AllsparkOfficialWebsiteApplication`（激活 `prod` profile）。

### 5. 访问
- 接口文档：http://localhost:8721/api/doc.html （文档登录账号 `lotus`，密码 `allspark520`）
- 报名接口：`POST http://localhost:8721/api/register`
- 运营端登录：`POST http://localhost:8721/api/admin/login`

## 接口一览
| 方法 | 路径 | 说明 |
| --- | --- | --- |
| POST | `/register` | 报名 |
| POST | `/admin/login` | 运营端登录（默认 `admin / allspark520`） |
| POST | `/admin/logout` | 退出登录 |
| GET | `/admin/me` | 当前登录管理员信息 |
| GET | `/admin/users` | 分页查询报名数据，参数 `current/size/userName/direction/grade/qqNumber` |
| GET | `/admin/users/export` | 导出报名数据为 Excel，参数 `direction/grade` 可选 |
| DELETE | `/admin/user/{id}` | 删除报名记录 |

## 说明
- 统一返回格式：`{code, data, message}`，`code = 200` 表示成功。
- `/admin/**` 接口需要登录，登录后通过 Session 保持状态，未登录返回 `40100`。
- 密码规则：`sha256("allspark" + 用户名 + ":" + 明文密码)`。
- `application-prod.yaml` 当前为本地开发配置并随仓库提交；上线部署时请改用环境变量或配置中心，不要把生产密码提交到仓库。