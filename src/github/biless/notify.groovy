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
