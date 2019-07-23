package main

import (
	"fmt"
	"github.com/Shopify/sarama"
	cluster "github.com/bsm/sarama-cluster"
	"log"
	"os"
	"os/signal"
	"sync"
)

/**
 *@Author  zct
 *@Date  2019-07-19
 *@Description
 */

var AddressConsumer = []string{"localhost:9092"}

func main()  {
	topic := []string{"test"}
	var wg = &sync.WaitGroup{}
	wg.Add(2)
	//广播式消费：消费者1
	go clusterConsumer(wg, AddressConsumer, topic, "group-1")
	//广播式消费：消费者2
	go clusterConsumer(wg, AddressConsumer, topic, "group-2")

	wg.Wait()
}

func checkErr2(err error)  {
	if err!=nil{
		panic(err)
	}
}

func clusterConsumer(wg *sync.WaitGroup,brokers, topics []string, groupId string)  {
	defer wg.Done()
	config:=cluster.NewConfig()
	config.Consumer.Return.Errors=true
	config.Group.Return.Notifications=true
	config.Consumer.Offsets.Initial=sarama.OffsetNewest

	consumer,err:=cluster.NewConsumer(brokers,groupId,topics,config)
	checkErr2(err)
	defer consumer.Close()

	signals:=make(chan os.Signal,1)
	signal.Notify(signals,os.Interrupt)

	go func() {
		for err := range consumer.Errors() {
			log.Printf("%s:Error: %s\n", groupId, err.Error())
		}
	}()

	go func() {
		for ntf := range consumer.Notifications() {
			log.Printf("%s:Rebalanced: %+v \n", groupId, ntf)
		}
	}()

	var successes int
	Loop:
	for {
		select {
		case msg, ok := <-consumer.Messages():
			if ok {
				fmt.Fprintf(os.Stdout, "%s:%s/%d/%d\t%s\t%s\n", groupId, msg.Topic, msg.Partition, msg.Offset, msg.Key, msg.Value)
				consumer.MarkOffset(msg, "")  // mark message as processed
				successes++
			}
		case <-signals:
			break Loop
		}
	}
	fmt.Fprintf(os.Stdout, "%s consume %d messages \n", groupId, successes)
}