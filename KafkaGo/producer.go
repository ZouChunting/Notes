package main

import (
	"fmt"
	"github.com/Shopify/sarama"
	"os"
	"time"
)

/**
 *@Author  zct
 *@Date  2019-07-19
 *@Description
 */

var Address = []string{"localhost:9092"}

func main()  {
	syncProducer(Address)
}

func checkErr(err error)  {
	if err!=nil{
		panic(err)
	}
}

func syncProducer(address []string)  {
	config:=sarama.NewConfig()
	config.Producer.Return.Successes=true
	config.Producer.Timeout=5*time.Second
	producer,err:=sarama.NewSyncProducer(address,config)
	checkErr(err)
	defer producer.Close()

	topic:="test"
	srcValue:="sync: this is a message. index=%d"
	for i:=0;i<10;i++{
		value:=fmt.Sprintf(srcValue,i)
		msg:=&sarama.ProducerMessage{
			Topic:topic,
			Value:sarama.ByteEncoder(value),
		}
		part,offset,err:=producer.SendMessage(msg)
		checkErr(err)
		fmt.Fprintf(os.Stdout, value + "发送成功，partition=%d, offset=%d \n", part, offset)
		time.Sleep(2*time.Second)
	}

}