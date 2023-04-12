package com.ijunfu.batch.chapter01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 *
 * @title  : Job 配置类
 *  @EnableBatchProcessing 启用SpringBatch功能并提供用于设计批处理作业的基本配置
 * @author : ijunfu <ijunfu@163.com>
 * @date   : 2023/4/12 23:39
 * @version: 1.0
 * @motto  : 位来可期 - 星光不问赶路人 时光不负有心人
 *
 */
@Slf4j
@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    /**
     * @title  : 定义一个名称为 MyJob 的 作业
     * @param  : step
     * @return : org.springframework.batch.core.Job
     * @author : ijunfu <ijunfu@163.com>
     * @date   : 2023/4/13 0:06
     * @motto  : 位来可期 - 星光不问赶路人 时光不负有心人
     */
    @Bean
    public Job job(Step step) {
        return jobBuilderFactory.get("MyJob").start(step).build();
    }

    /**
     * @title  : 定义一个名称为MyStep的步骤
     * @param  : tasklet
     * @return : org.springframework.batch.core.Step
     * @author : ijunfu <ijunfu@163.com>
     * @date   : 2023/4/13 0:07
     * @motto  : 位来可期 - 星光不问赶路人 时光不负有心人
     */
    @Bean
    public Step step(Tasklet tasklet) {
        return stepBuilderFactory.get("MyStep").tasklet(tasklet).build();
    }

    /**
     * @title  : 定义一个步骤的执行策略
     * @param  :
     * @return : org.springframework.batch.core.step.tasklet.Tasklet
     * @author : ijunfu <ijunfu@163.com>
     * @date   : 2023/4/13 0:07
     * @motto  : 位来可期 - 星光不问赶路人 时光不负有心人
     */
    @Bean
    public Tasklet tasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                log.info("Hello Spring Batch!");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
