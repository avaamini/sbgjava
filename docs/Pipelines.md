# Pipelines

To manage pipelines using the [Seven Bridges Genomics API](https://docs.sbgenomics.com/display/developerhub/API%3A+Pipelines), construct an instance of the `Pipelines` class using your authentication token. 
```java
String authToken = "c6d0207bed2041a5a2293f7e171b6795";
Pipelines pipelineRequests = new Pipelines(authToken);
```

### getPublicPipelines(): Get public pipelines

Use this call to return the IDs and names of all public pipelines on the Seven Bridges Platform. 

<h6> Example Request </h6> 

```java
pipelineRequests.getPublicPipelines();
```

<h6> Example Response </h6> 

```java
{
  "items": [
    {
      "id": "534522d3d79f0049c0c9444d",
      "name": "Targeted Capture Analysis - BWA + GATK 2.3.9-Lite (with Metrics)"
    },
    {
      "id": "534522f6d79f0049c0c9444e",
      "name": "Whole Exome Analysis - BWA + GATK 2.3.9-Lite (with Metrics)"
    },
    {
      "id": "546ba1bbd79f00701cb276a6",
      "name": "Cellular Research Precise\u2122 Analysis Pipeline"
    },
    {
      "id": "534520eed79f0049c0c9443a",
      "name": "FastQC Analysis"
    },
    {
      "id": "534521e5d79f0049c0c94445",
      "name": "RNA-Seq Alignment for Ion Proton - TopHat + Bowtie 2"
    },
 
    <snip>
  
    {
      "id": "5345205cd79f0049c0c9442f",
      "name": "Convert SAM\/BAM to FASTQ"
    }
  ]
}
```

### getMyPipelines(): Get my pipelines

Use this call to return the IDs and names of all the pipelines in "My Pipelines". 

<h6> Example Request </h6> 

```java
pipelineRequests.getMyPipelines();
```

<h6> Example Response </h6> 

```java
{
    "items": [
        {
            "id": "54de1698896a5d102fc8387f",
            "name": "Alignment Metrics QC"
        }
    ]
}
```

### getProjectPipelines(String projectID): Get pipelines in a project

Use this call to obtain a list of all the pipelines in a given project, specified by the parameter projectID. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
pipelineRequests.getProjectPipelines(projectID);
```

<h6> Example Response </h6> 

```java
{
  "items": [
    {
      "id": "423",
      "name": "Split Read Alignment for RNA-Seq - TopHat",
      "description": "This pipeline employs the split-read aligner, TopHat, to map sequence reads from RNA-seq experiments to a reference transcriptome. This pipeline will generate alignment files that can be compared for differential expression, analyzed to discover novel transcripts, or viewed directly in a genome browser.
        <snip> "
    },
    {
      "id": "422",
      "name": "Exome Variant Calling - BWA + GATK",
      "description": "Variant Calling for Single Samples aligns FASTQ sequencing read files to a reference genome and identifies single nucleotide polymorphisms (SNPs) and short insertions and deletions (indels).\n\nBased on the default bioinformatic pipeline employed by The Broad Institute and the 1000 Genomes Project, it processes data from both single- and paired-end experiments. 
        <snip>"
    }
  ]
}
```

### getPipelineDetails(String projectID, String pipelineID): Get pipeline details

Use this call to obtain the details of a given pipeline in a given project, including the pipeline's runtime, description, and regular parameters. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String pipelineID = "550c0beb727ab720186db837";
getPipelineDetails(projectID, pipelineID);
```

<h6> Example Response </h6> 

```java
{
  "id": "550c0beb727ab720186db837",
  "name": "Exome Variant Calling - BWA + GATK",
  "description": "Variant Calling for Single Samples aligns FASTQ sequencing read files to a reference genome and identifies single nucleotide polymorphisms (SNPs) and short insertions and deletions (indels).\n\nBased on the default bioinformatic pipeline employed by The Broad Institute and the 1000 Genomes Project, it processes data from both single- and paired-end experiments. It can accept FASTQ file(s) for one or more samples as an input and outputs a single variant calling format (VCF) file per sample.This pipeline will generate indexed BAM alignment files so that you can view your aligned reads directly in a genome browser.\n\nBefore you run this pipeline:\n\nSometime sequence reads for a single sample are split over multiple files to reduce individual file size. If this is the case for your data, please use the \"Merge FASTQ Files\" pipeline first, so that you have one FASTQ file per sample (or two FASTQ files, for paired-end data).",
  "revision": "110",
  "inputs": [
    {
      "id": 309485,
      "name": "Known Indels",
      "required": false,
      "accepts_list": true,
      "file_types": [
          "vcf"
      ]
    },
    {
      "id": 317344,
      "name": "Reference Genome",
      "required": false,
      "accepts_list": false,
      "file_types": [
          "fasta"
      ]
    },
    {
      "id": 318662,
      "name": "Known SNPs",
      "required": false,
      "accepts_list": false,
      "file_types": [
          "vcf"
      ]
    },
    {
      "id": 699018,
      "name": "FASTQ Reads",
      "required": false,
      "accepts_list": false,
      "file_types": []
    }
  ],
  "nodes": [
    {
      "id": 383791,
      "name": "FASTQ Quality Scale Detector",
      "application_id": "sbgtools.fqsniff"
    },
    {
      "id": 393463,
      "name": "BWA",
      "application_id": "bwa.bwa",
      "parameters": [
        {
          "id": "rg_seq_tech",
          "type": "enum",
          "required": false,
          "values": [
            "454",
            "Helicos",
            "Illumina",
            "Solid",
            "IonTorrent"
          ]
        },
        {
          "id": "read_trimming_qual",
          "type": "integer",
          "required": false
        }
      ]
    },
    {
      "id": 442861,
      "name": "GATK Base Quality Score Recalibrator",
      "application_id": "gatk.recal"
    },
    {
      "id": 506029,
      "name": "GATK IndelRealigner",
      "application_id": "gatk.realign"
    },
    {
      "id": 547271,
      "name": "SAM to BAM",
      "application_id": "samtools.sam2bam"
    },
    {
      "id": 548467,
      "name": "Picard Sort",
      "application_id": "picard.sort"
    },
    {
      "id": 549967,
      "name": "Picard Mark Duplicate",
      "application_id": "picard.dedupe"
    },
    {
      "id": 677492,
      "name": "GATK Unified Genotyper",
      "application_id": "gatk.genotyper",
      "parameters": [
        {
          "id": "stand_emit_conf",
          "type": "float",
          "required": false
        },
        {
          "id": "stand_call_conf",
          "type": "float",
          "required": false
        }
      ]
    },
    {
      "id": 300364,
      "name": "Picard BuildBamIndex",
      "application_id": "picard.bamindex"
    },
    {
      "id": 313666,
      "name": "Picard BuildBamIndex",
      "application_id": "picard.bamindex"
    },
    {
      "id": 327269,
      "name": "Picard BuildBamIndex",
      "application_id": "picard.bamindex"
    },
    {
      "id": 915326,
      "name": "Picard BuildBamIndex",
      "application_id": "picard.bamindex"
    }
  ],
  "outputs": [
    {
      "id": 306733,
      "name": "Variant Calling Files (VCFs)"
    },
    {
      "id": 797847,
      "name": "Recalibration Plots"
    },
    {
      "id": 859496,
      "name": "Processed BAMs"
    },
    {
      "id": 1812094,
      "name": "BAM index files (BAI)"
    }
  ]
}
```

### addPipeline(String destinationProjectID, String pipelineID, String revision, String sourceProjectID): Add a pipeline

Use this call to add a pipeline to a specified project, by copying the pipeline over from "My Pipelines", "Public Pipelines", or from another project. 

<h6> Example Request </h6> 

```java 
// to copy a pipeline from "My Pipelines"
String destinationProject = "8e885662-c891-4227-bdb5-d87a90f3f461";
String pipelineID = "53452130d79f0049c0c94441";
String revision = "";
String sourceProject = "my";
pipelineRequests.addPipeline(destinationProject, pipelineID, revision, sourceProject);

// to copy a pipeline from "Public Pipelines"
String destinationProject = "8e885662-c891-4227-bdb5-d87a90f3f461";
String pipelineID = "53452130d79f0049c0c94441";
String revision = "";
String sourceProject = "";
pipelineRequests.addPipeline(destinationProject, pipelineID, revision, sourceProject);

// to copy a pipeline from another project
String destinationProject = "8e885662-c891-4227-bdb5-d87a90f3f461";
String pipelineID = "53452130d79f0049c0c94441";
String revision = "";
String sourceProject = "130d3136-eb47-4bea-98e4-96225a89f647";
```

<h6> Example Response </h6> 

```java
{
    "id": "54f47cb6896a5d12e08a9d48",
    "name": "Targeted Capture Analysis - BWA + GATK 2.3.9-Lite (with Metrics)",
    "description": "Based on the most recent free release of the default bioinformatic pipeline employed by The Broad Institute and the 1000 Genomes Project, this is the standard Public Pipeline for analyzing data from targeted capture sequencing experiments on the Seven Bridges Platform. This pipeline is intended for with data from experiments covering portions of the genome that are smaller than whole exomes (for whole exome data see the Whole Exome version of the pipeline)\ \ This pipeline aligns sequence read files to a reference genome and identifies single nucleotide polymorphisms (SNPs) and short insertions and deletions (INDELs). It processes data from both single- and paired-end experiments and can accept BAM for one or more samples as input. The output is a single VCF file per sample. This pipeline will generate indexed BAM alignment files so that aligned reads can be viewed directly in a genome browser. The produced VCF is filtered with the hard filters, as recommended by the Broad’s best practices.",
    "revision": "0"
}
```

