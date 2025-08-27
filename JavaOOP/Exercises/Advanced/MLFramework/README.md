# Bài tập: Machine Learning Framework với OOP

## Mục tiêu:

Xây dựng framework cho machine learning với focus vào extensibility và reusability

## Yêu cầu chi tiết:

### 1. Abstract Class: MLModel<T, R>

- Generic types: T (input type), R (result type)
- Fields: `modelName`, `version`, `trainedAt`, `accuracy`, `parameters`
- Abstract method: `train(Dataset<T> trainingData)`
- Abstract method: `predict(T input)` returns R
- Abstract method: `evaluate(Dataset<T> testData)` returns ModelMetrics
- Method: `save(String filePath)`, `load(String filePath)`
- Method: `getModelInfo()` returns ModelInfo

### 2. Interface: Trainable<T>

- Method: `train(Dataset<T> data, TrainingConfig config)`
- Method: `isTrainingRequired()`
- Method: `getTrainingProgress()`
- Method: `stopTraining()`

### 3. Interface: Evaluable<T, R>

- Method: `evaluate(Dataset<T> testData)`
- Method: `crossValidate(Dataset<T> data, int folds)`
- Method: `getMetrics()` returns Map<String, Double>

### 4. Interface: Serializable (Custom)

- Method: `serialize()` returns byte[]
- Method: `deserialize(byte[] data)`
- Method: `getSerializationVersion()`

### 5. Concrete Model Classes:

#### LinearRegressionModel extends MLModel<double[], Double> implements Trainable, Evaluable

- Fields: `weights`, `bias`, `learningRate`, `maxIterations`
- Method: `gradientDescent(Dataset<double[]> data)`
- Method: `calculateLoss(Dataset<double[]> data)`
- Method: `updateWeights(double[] gradients)`
- Implement prediction cho continuous values

#### LogisticRegressionModel extends MLModel<double[], Boolean> implements Trainable, Evaluable

- Fields: `weights`, `bias`, `threshold`
- Method: `sigmoid(double x)`
- Method: `calculateLogLoss(Dataset<double[]> data)`
- Method: `classifyProbability(double[] input)` returns double
- Binary classification implementation

#### DecisionTreeModel extends MLModel<Map<String, Object>, String> implements Trainable, Evaluable

- Fields: `rootNode`, `maxDepth`, `minSamplesLeaf`, `features`
- Method: `buildTree(Dataset<Map<String, Object>> data, int depth)`
- Method: `findBestSplit(Dataset<Map<String, Object>> data)`
- Method: `calculateGini(List<String> labels)`
- Handle categorical và numerical features

#### NeuralNetworkModel extends MLModel<double[], double[]> implements Trainable, Evaluable

- Fields: `layers`, `activationFunction`, `optimizer`, `lossFunction`
- Method: `forwardPass(double[] input)`
- Method: `backwardPass(double[] target)`
- Method: `updateWeights(double learningRate)`
- Support cho multiple hidden layers

### 6. Data Handling Classes:

#### Class: Dataset<T>

- Fields: `data` (List<DataPoint<T>>), `features`, `labels`
- Method: `shuffle()`
- Method: `split(double trainRatio)` returns Pair<Dataset<T>, Dataset<T>>
- Method: `normalize()` - feature scaling
- Method: `addDataPoint(T features, Object label)`
- Method: `getStatistics()` returns DataStatistics

#### Class: DataPoint<T>

- Fields: `features` (T), `label` (Object), `weight` (double)
- Method: `getFeature(String name)`
- Method: `setWeight(double weight)`

#### Interface: DataPreprocessor<T>

- Method: `preprocess(Dataset<T> data)`
- Method: `transform(T input)`
- Method: `fit(Dataset<T> data)` - learn preprocessing parameters

### 7. Feature Engineering:

#### Abstract Class: FeatureExtractor<T, R>

- Abstract method: `extractFeatures(T input)` returns R
- Method: `getFeatureNames()` returns List<String>
- Method: `getFeatureTypes()` returns Map<String, FeatureType>

#### Concrete Feature Extractors:

- `TextFeatureExtractor` - TF-IDF, word counts, n-grams
- `ImageFeatureExtractor` - pixel intensities, edges, histograms
- `NumericFeatureExtractor` - scaling, binning, polynomial features

### 8. Model Training Pipeline:

#### Class: TrainingPipeline<T, R>

- Fields: `preprocessors`, `featureExtractors`, `model`, `validators`
- Method: `addPreprocessor(DataPreprocessor<T> processor)`
- Method: `addFeatureExtractor(FeatureExtractor<T, ?> extractor)`
- Method: `setModel(MLModel<T, R> model)`
- Method: `train(Dataset<T> data)` - execute entire pipeline
- Method: `predict(T input)` - preprocessing + prediction

### 9. Model Validation:

#### Interface: ValidationStrategy<T, R>

- Method: `validate(MLModel<T, R> model, Dataset<T> data)`
- Method: `getValidationMetrics()`

#### Implementations:

- `HoldoutValidation` - simple train/test split
- `CrossValidation` - k-fold cross validation
- `TimeSeriesValidation` - time-based splitting
- `StratifiedValidation` - maintain class distribution

### 10. Optimization Framework:

#### Interface: Optimizer

- Method: `optimize(OptimizationProblem problem)`
- Method: `getCurrentSolution()`
- Method: `getOptimizationHistory()`

#### Abstract Class: OptimizationProblem

- Abstract method: `evaluate(Solution solution)` returns double
- Abstract method: `generateRandomSolution()`
- Method: `isValidSolution(Solution solution)`

#### Implementations:

- `GradientDescentOptimizer`
- `GeneticAlgorithmOptimizer`
- `RandomSearchOptimizer`
- `BayesianOptimizer`

### 11. Ensemble Methods:

#### Abstract Class: EnsembleModel<T, R> extends MLModel<T, R>

- Fields: `baseModels`, `combiningStrategy`
- Abstract method: `combinePredictons(List<R> predictions)`
- Method: `addBaseModel(MLModel<T, R> model)`
- Method: `trainEnsemble(Dataset<T> data)`

#### Implementations:

- `VotingEnsemble` - majority voting for classification
- `AveragingEnsemble` - average predictions for regression
- `StackingEnsemble` - meta-model combines base predictions
- `BoostingEnsemble` - sequential model training

### 12. Performance Monitoring:

#### Class: ModelMetrics

- Fields: `accuracy`, `precision`, `recall`, `f1Score`, `confusionMatrix`
- Method: `calculateROC()` returns ROCCurve
- Method: `calculateAUC()` returns double
- Method: `getClassificationReport()` returns String

#### Class: PerformanceTracker

- Method: `startTracking(String modelName)`
- Method: `recordMetric(String metricName, double value)`
- Method: `recordPredictionTime(long milliseconds)`
- Method: `generateReport()` returns PerformanceReport

### 13. Model Registry:

#### Interface: ModelRegistry

- Method: `registerModel(String name, MLModel<?, ?> model)`
- Method: `getModel(String name, String version)`
- Method: `listModels()` returns List<ModelInfo>
- Method: `deleteModel(String name, String version)`

#### Class: FileBasedModelRegistry implements ModelRegistry

- Store models in filesystem với versioning
- Method: `getModelPath(String name, String version)`
- Method: `saveModelMetadata(ModelInfo info)`

### 14. Advanced Features:

#### Hyperparameter Tuning:

```java
public class HyperparameterTuner<T, R> {
    public OptimizationResult tune(MLModel<T, R> model,
                                  Dataset<T> data,
                                  ParameterSpace space,
                                  TuningStrategy strategy)
}
```

#### Model Explanation:

```java
public interface ModelExplainer<T, R> {
    Explanation explain(MLModel<T, R> model, T input);
    FeatureImportance getFeatureImportance();
}
```

#### Online Learning:

```java
public interface OnlineLearner<T, R> {
    void partialFit(T input, R target);
    R predictOnline(T input);
    void adaptToConceptDrift();
}
```

### 15. Distributed Training:

#### Interface: DistributedTrainer<T, R>

- Method: `distributeTraining(List<Dataset<T>> partitions)`
- Method: `aggregateModels(List<MLModel<T, R>> models)`
- Method: `synchronizeParameters()`

#### Implementation considerations:

- Parameter server architecture
- Federated learning support
- Gradient aggregation strategies
- Communication optimization

### 16. Integration với External Libraries:

#### Adapter Pattern implementations:

- `WEKAModelAdapter` - wrap WEKA algorithms
- `TensorFlowModelAdapter` - integrate TensorFlow models
- `ScikitLearnAdapter` - bridge to Python models
- `SparkMLAdapter` - Apache Spark ML integration

### 17. Configuration và Lifecycle:

#### Class: MLConfiguration

- Model hyperparameters
- Training configurations
- Resource allocations
- Logging settings

#### Class: ModelLifecycleManager

- Method: `deploy(MLModel<?, ?> model)`
- Method: `monitor(String modelName)`
- Method: `retrain(String modelName, Dataset<?> newData)`
- Method: `retire(String modelName)`

### 18. Testing Framework:

- Model accuracy tests với known datasets
- Performance regression tests
- Memory usage tests
- Reproducibility tests với random seeds
- A/B testing framework cho model comparison

## Technical Requirements:

- Thread-safe implementations cho concurrent training
- Memory-efficient data structures cho large datasets
- Proper resource management (memory, files, threads)
- Extensive logging và debugging support
- Configuration management cho different environments

## Đánh giá:

- OOP design quality (25%)
- Framework extensibility (20%)
- Algorithm correctness (20%)
- Performance optimization (20%)
- Documentation và examples (15%)
