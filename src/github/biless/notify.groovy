package github.biless

def SendWxWork(Status,CatchInfo=' ') {
  echo "!--------- Send WxWork......"
	sh """
		curl --location --request POST 'https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=$appToken' \
		--header 'Content-Type: application/json' \
		--data '{
            "msgtype": "markdown",
            "markdown": {
              "content": "### 生产发布申请,请【${adminUser}】审批@${approvalDD}\n- 应用名称: ${env.JOB_NAME}\n- 构建信息: ${Status}${CatchInfo}\n- 随机验证码: ${randomToken}",
              "mentioned_list": [ "@all" ]
            }
        }'
	"""
}

// 发送审批信息
def SendApprovalWxWork(AppToken,ApprovalUser,JobName,Info,ApprovalCode = '') {
  echo "!--------- Send WxWork --------------"
	sh """
		curl --location --request POST 'https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=$AppToken' \
		--header 'Content-Type: application/json' \
		--data '{
            "msgtype": "markdown",
            "markdown": {
              "content": "### 【${ApprovalUser}】发布上线申请,请审批\n> 应用名称: ${JobName}\n> 构建信息: ${Info}\n> 随机验证码: ${ApprovalCode}\n <@all>"
            }
        }'
	"""
}

// 发送上线信息
def SendStatusWxWork(appToken,approvalUser,status,jobName) {
  statusDes = "<font color=\"info\">已完成</font>"
  status = status ?: "failure"
  if (status == "success") {

  } else if (status == "failure") {
    statusDes = "<font color=\"warning\">失败</font>"
  } else {
    statusDes = "<font color=\"comment\">已取消</font>"
  }

  echo "!--------- Send WxWork --------------"
	sh """
		curl --location --request POST 'https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=$appToken' \
		--header 'Content-Type: application/json' \
		--data '{
            "msgtype": "markdown",
            "markdown": {
              "content": "### 【${approvalUser}】发布上线申请\n> 应用名称: ${jobName}\n> 构建结果: ${statusDes}\n <@all>",
              "mentioned_list": [ "@all" ]
            }
        }'
	"""
  }
